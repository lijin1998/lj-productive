package cn.lj.client;


import cn.lj.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("item-service" )
public interface CategoryClient extends CategoryApi {

}
