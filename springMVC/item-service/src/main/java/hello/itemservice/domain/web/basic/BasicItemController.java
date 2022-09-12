package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
    테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 1000,10));
        itemRepository.save(new Item("itemB", 2000,20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,
                            Model model){
        itemRepository.save(item);
//        model.addAttribute("item", item); //@ModelAttribute("item")여기서 해줬기에 해당코드 필요없음

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Item item){
        //이건 model.addAttribute("item",item)과 같음. 타입이 지금 Item인데 타입의 첫글자를 소문자로 바꿔서 키로 저장함.
        itemRepository.save(item);
        return "basic/item";
    }

    /*
    ModelAttribute생략가능! 기본타입들은 RequestParam이 적용되지만
    그 외 타입은 ModelAttribute이 적용됨
     */
    //@PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    /**
    주의! 리다이렉트를 하지 않으면, 브라우저에서 새로고침하면 이전에 했던 행위를 그대로 하므로
     데이터를 또 전송해서 등록이 되어버리는 사고가 발생한다.
     그래서 redirect를 해주면 브라우저가 다시 해당 url로 요청하는 것이 되므로,
     새로고침해도 get만 됨.
     */
    //@PostMapping("/add")
    public String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());//pathVariable로 바인딩
        redirectAttributes.addAttribute("status", true);// 얘는 쿼리파라미터로 처리됨 -> basic/item.html에서 ${param.status} 이케 쓰임
        return "redirect:/basic/items/{itemId}";
    }

    //========수정=============
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);

        //뷰 템플릿을 호출하는 대신에 상품상세화면을 이동하도록 다시 컨트롤러부터 호출
        return "redirect:/basic/items/{itemId}";
    }
}
