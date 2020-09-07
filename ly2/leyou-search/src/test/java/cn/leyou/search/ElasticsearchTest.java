package cn.leyou.search;

import cn.leyou.search.client.GoodsClient;
import cn.leyou.search.pojo.Goods;
import cn.leyou.search.repository.GoodsRepository;
import cn.leyou.search.service.SearchService;
import cn.lj.common.pojo.PageResult;
import cn.lj.item.bo.SpuBo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchApplication.class)
public class ElasticsearchTest {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;

    @Autowired
    private GoodsRepository goodsRepository;


    @Test
    public void loadData() throws IOException {

        int page = 1;
        int rows = 100;
        int size = 0;

        do {
            // 查询spu信息
            PageResult<SpuBo> result = goodsClient.querySpuByPage(page, rows, true, null);
            List<SpuBo> spuList = result.getItems();

            if(CollectionUtils.isEmpty(spuList)){
                break;
            }

            // 构建成goods
            List<Goods> goodsList = new ArrayList<>();
            SearchService searchService1 = searchService;
            for (SpuBo spuBo : spuList) {
                Goods goods = searchService1.buildGoods(spuBo);
                goodsList.add(goods);
            }
            // 上面是流的写法，用foreach也可以实现
/*        for (Spu spu : spuList) {
            searchService.buildGoods(spu);
        }*/

            // 存入索引库
            goodsRepository.saveAll(goodsList);

            // 翻页
            page++;
            size = spuList.size();
        }while (size == 100);//等于这一页说明当前页查满了，可能会有下一页继续查询，如果当前没有满，说明是最后一页了，退出循环
    }
}