package backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.entity.AppType;
import backend.entity.Good;
import backend.service.AppTypeService;
import backend.service.GoodService;

@Controller
public class GoodController {
    @Autowired
    private GoodService goodService;

    @Autowired
    private AppTypeService appTypeService;
    
    @GetMapping("goods")
    public String getGoods(Model model) {
        List<Good> goods = goodService.findAll();
        model.addAttribute("goods", goods);
        return "goods";
    }

    @GetMapping("/good-info")
    public String getGoodInfo(@RequestParam(name = "good-id") Long id,
            Model model) {
        Optional<Good> foundGood = goodService.findById(id);
        if (foundGood.isPresent()) {
            Good good = foundGood.get();
            model.addAttribute("good", good);
            return "good-info";
        } else {
            model.addAttribute("error", new ErrorMsg("There is no good with id=" + id));
            return "invalid-action";
        }
    }

    /**
     * If given good id is null then adds new good.
     * Otherwise returns good info (to let then update it).
     * @param id good id
     * @param model
     * @return
     */
    @GetMapping("/good-edit")
    public String editGoodInfo(@RequestParam(name = "good-id", required = false) Long id,
            Model model) {
        List<AppType> appTypes = appTypeService.findAllByOrderByName();
        if (id == null) {
            model.addAttribute("good", new Good());
            model.addAttribute("types", appTypes);
            return "good-edit";
        } else {
            Optional<Good> foundGood = goodService.findById(id);
            if (!foundGood.isPresent()) {
                model.addAttribute("error", new ErrorMsg("There is no good with id=" + id + ", so cannot edit"));
                return "invalid-action";
            } else {
                Good good = foundGood.get();
                model.addAttribute("good", good);
                model.addAttribute("types", appTypes);
                return "good-edit";
            }
        }
    }

    /**
     * If given id is null then saves new good with given characteristics.
     * Otherwise updates good info.
     * @param id good id
     * @param name
     * @param address
     * @param email
     * @param number
     * @param model
     * @return
     */
    @PostMapping("/good-save")
    public String saveGoodInfo(@RequestParam(name = "good-id", required = false) Long id,
            @RequestParam(name = "good-name") String name,
            @RequestParam(name = "good-price") Double price,
            @RequestParam(name = "good-company") String company,
            @RequestParam(name = "good-assembly-place") String assemblyPlace,
            @RequestParam(name = "good-quantity") Integer quantity,
            @RequestParam(name = "good-description", required = false) String description,
            @RequestParam(name = "good-type-id") Long appTypeId,
            Model model) {
        boolean successfullySaved = false;
        Good savedGood = null;

        if (price <= 0.0) {
            model.addAttribute("error",
                    new ErrorMsg("The price must be a positive real number!"));
            return "invalid-action";
        }

        if (quantity < 0) {
            model.addAttribute("error",
                    new ErrorMsg("The quantity must be a natural number!"));
            return "invalid-action";
        }

        Optional<AppType> foundAppType = appTypeService.findById(appTypeId);
        if (!foundAppType.isPresent()) {
            model.addAttribute("error",
                    new ErrorMsg("Experienced some trouble. Cannot find app type with id="
                    + id
                    + ".\n Please, try again."));
            return "invalid-action";
        }
        AppType appType = foundAppType.get();

        if (id == null) {
            Good good = new Good(appType, name,
                    price, company, assemblyPlace,
                    quantity, description);
            Good newGood = goodService.save(good);
            successfullySaved = (newGood.getId() != null);
            if (successfullySaved) {
                savedGood = newGood;
            }
        } else {
            Optional<Good> foundGood = goodService.findById(id);
            if (!foundGood.isPresent()) {
                model.addAttribute("error", new ErrorMsg("There is no good with id=" + id + ", so cannot update"));
                return "invalid-action";
            } else {
                Good good = foundGood.get();
                good.setAppType(appType);
                good.setName(name);
                good.setPrice(price);
                good.setCompany(company);
                good.setAssemblyPlace(assemblyPlace);
                good.setQuantity(quantity);
                good.setDescription(description);
                Optional<Good> updatedGood = goodService.update(good);
                successfullySaved = updatedGood.isPresent();
                if (successfullySaved) {
                    savedGood = updatedGood.get();
                }
            }
        }

        if (successfullySaved) {
            String redirect = "redirect:/good-info?good-id=" + savedGood.getId();
            return redirect;
        } else {
            model.addAttribute("error", new ErrorMsg("Cannot save good info"));
            return "invalid-action";
        }
    }

    @PostMapping("/good-delete")
    public String deleteGood(@RequestParam(name = "good-id") Long id,
            Model model) {
        Optional<Good> foundGood = goodService.findById(id);
        if (!foundGood.isPresent()) {
            model.addAttribute("error", new ErrorMsg("There is no good with id=" + id));
            return "invalid-action";
        } else {
            Good good = foundGood.get();
            if (good.getOrders().size() > 0) {
                model.addAttribute("error",
                        new ErrorMsg("Cannot delete item "
                        + good.getName()
                        + ". There are orders containing it."));
                return "invalid-action";
            } else {
                goodService.delete(foundGood.get());
                return "redirect:/goods";
            }
        }
    }
}

