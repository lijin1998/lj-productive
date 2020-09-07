package cn.lj.cart.service;

import cn.lj.auto.pojo.UserInfo;
import cn.lj.cart.client.GoodsClient;
import cn.lj.cart.interceptor.LoginInterceptor;
import cn.lj.cart.pojo.Cart;
import cn.lj.common.utils.JsonUtils;
import cn.lj.item.pojo.Sku;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private GoodsClient goodsClient;

    static final String KEY_PREFIX = "leyou:cart:uid:";

    static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public void addCart(Cart cart) {
        // 获取登录用户
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        // Redis的key
        String key = KEY_PREFIX + userInfo.getId();
        // 获取hash操作对象
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        // 查询是否存在
        Long skuId = cart.getSkuId();
        String keys = skuId.toString();
        Integer num = cart.getNum();
        Boolean boo = hashOps.hasKey(keys);
        if (boo) {
            // 存在，获取购物车数据
            String json = hashOps.get(keys).toString();
            cart = JsonUtils.parse(json, Cart.class);
            // 修改购物车数量
            cart.setNum(cart.getNum() + num);
//            hashOps.put(keys, JsonUtils.serialize(cart));
        } else {
            // 不存在，新增购物车数据
            cart.setUserId(userInfo.getId());
            // 其它商品信息，需要查询商品服务
            Sku sku = this.goodsClient.querySkuBySkuId(skuId);
            cart.setUserId(userInfo.getId());
            cart.setImage(StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            cart.setPrice(sku.getPrice());
            cart.setTitle(sku.getTitle());
            cart.setOwnSpec(sku.getOwnSpec());
//            hashOps.put(keys,cart);
        }
        // 将购物车数据写入redis
        hashOps.put(keys, JsonUtils.serialize(cart));
    }

    public List<Cart> queryCartList() {
        // 获取登录用户信息
        UserInfo userInfo = LoginInterceptor.getLoginUser();

        //判断是否存在购物车
        String key = KEY_PREFIX + userInfo.getId();
        if(!this.redisTemplate.hasKey(key)) {
            return null;
        }

        // 查询购物车数据
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        List<Object> cartsJson = hashOps.values();

        // 判断是否有数据
        if(CollectionUtils.isEmpty(cartsJson)){
            return null;
        }

        //把List<Object>集合转换为List<Cart>集合
        return cartsJson.stream().map(o -> JsonUtils.parse(o.toString(), Cart.class)).collect(Collectors.toList());
    }

    public void updateCarts(Cart cart) {
        // 获取登录用户信息
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        //判断是否存在购物车
        String key = KEY_PREFIX + userInfo.getId();
        if(!this.redisTemplate.hasKey(key)) {
            return ;
        }
        // 查询购物车数据
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);

        String cartJson = hashOps.get(cart.getSkuId().toString()).toString();

        Cart cart1 = JsonUtils.parse(cartJson,Cart.class);

        cart1.setNum(cart.getNum());

        // 将购物车数据写入redis
        hashOps.put(cart.getSkuId().toString(), JsonUtils.serialize(cart1));
    }

    public void deleteCart(String skuId) {
        // 获取登录用户
        UserInfo user = LoginInterceptor.getLoginUser();
        String key = KEY_PREFIX + user.getId();
        BoundHashOperations<String, Object, Object> hashOps = this.redisTemplate.boundHashOps(key);
        hashOps.delete(skuId);
    }
}
