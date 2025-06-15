package com.example.message.util;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import com.example.message.been.PerformanceBeen;
@Component
public class ConfirmViewHelper {
    
public void setupConfirmModel(ModelAndView mv, PerformanceBeen been, 
                            Map<String, Integer> amounts, int totalCups, int totalSalesYen) {
        // 画面に表示させる用の値を渡す
        mv.addObject("white_beer_bottles", been.getWhiteBeerBottles());
        mv.addObject("white_amount", amounts.get("white"));
        
        mv.addObject("lager_bottles", been.getLagerBottles());
        mv.addObject("lager_yen", amounts.get("lager"));

        mv.addObject("pale_ale_bottles", been.getPaleAleBottles());
        mv.addObject("pale_ale_yen", amounts.get("pale"));

        mv.addObject("fruit_beer_bottles", been.getFruitBeerBottles());
        mv.addObject("fruit_beer_yen", amounts.get("fruit"));

        mv.addObject("black_beer_bottles", been.getBlackBeerBottles());
        mv.addObject("black_beer_yen", amounts.get("black"));

        mv.addObject("ipa_bottles", been.getIpaBottles());
        mv.addObject("ipa_yen", amounts.get("ipa"));

        mv.addObject("total_cups", totalCups);
        mv.addObject("total_sales_yen", totalSalesYen);
        mv.addObject("been", been);
    }
}
