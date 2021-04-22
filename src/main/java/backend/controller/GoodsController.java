package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backend.entity.Good;
import backend.service.GoodService;

@Controller
public class GoodsController {
    @Autowired
    private GoodService goodService;
    
    @GetMapping("goods")
    public String getGoods(Model model) {
        List<Good> goods = goodService.findAll();
        model.addAttribute("goods", goods);
        return "goods";
    }
}

