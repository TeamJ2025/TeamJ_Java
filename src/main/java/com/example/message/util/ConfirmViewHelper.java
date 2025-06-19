package com.example.message.util;

// 一時的にコメントアウト（SalesData エンティティが無効化されているため）
/*
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import com.example.message.entity.SalesData;

@Component
public class ConfirmViewHelper {

    public void setupConfirmModel(ModelAndView mv, SalesData salesData,
                                  Map<String, Integer> amounts, int totalCups, int totalSalesYen) {
        // 画面に表示させる用の値を渡す
        mv.addObject("white_beer_bottles", salesData.getWhiteBeerBottles());
        mv.addObject("white_beer_yen", amounts.get("white"));

        mv.addObject("lager_bottles", salesData.getLagerBottles());
        mv.addObject("lager_yen", amounts.get("lager"));

        mv.addObject("pale_ale_bottles", salesData.getPaleAleBottles());
        mv.addObject("pale_ale_yen", amounts.get("pale"));

        mv.addObject("fruit_beer_bottles", salesData.getFruitBeerBottles());
        mv.addObject("fruit_beer_yen", amounts.get("fruit"));

        mv.addObject("black_beer_bottles", salesData.getBlackBeerBottles());
        mv.addObject("black_beer_yen", amounts.get("black"));

        mv.addObject("ipa_bottles", salesData.getIpaBottles());
        mv.addObject("ipa_yen", amounts.get("ipa"));

        mv.addObject("total_cups", totalCups);
        mv.addObject("total_sales_yen", totalSalesYen);
        mv.addObject("salesData", salesData);  // HTMLに渡す用
    }
}
 */