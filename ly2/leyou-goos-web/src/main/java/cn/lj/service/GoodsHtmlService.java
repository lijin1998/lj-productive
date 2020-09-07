package cn.lj.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

@Service
public class GoodsHtmlService {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private GoodsService goodsService;

    /**
     * 创建html页面
     * @param spuId
     * @throws Exception
     */
    public void createHtml(Long spuId){
        PrintWriter writer = null;
        // 获取页面数据
        Map<String, Object> spuMap = this.goodsService.loadModel(spuId);
        // 创建thymeleaf上下文对象
        Context context = new Context();
        //把数据放入上下文对象
        context.setVariables(this.goodsService.loadModel(spuId));

        try {
            File file = new File("C:\\Intel\\nginx-1.14.0\\html\\item\\" + spuId + ".html");
            // 创建输出流
            writer = new PrintWriter(file);
            // 执行页面静态化方法
            templateEngine.process("item",context,writer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null){
                writer.close();
            }
        }
    }

    public void deleteIndex(Long id) {
        File file = new File("C:\\Intel\\nginx-1.14.0\\html\\item\\" + id + ".html");

        file.deleteOnExit();
    }
}
