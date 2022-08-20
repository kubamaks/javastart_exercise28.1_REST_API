package pl.km.exercise281.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.km.exercise281.category.Category;
import java.util.List;

@RequestMapping("api/offers")
@RestController
public class OfferRestController {
    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("")
    public List<OfferDto> getOffersWithParams(@RequestParam(name = "title", required = false) String titleParam) {
        return offerService.getOfferDtoListWithOptionalTitleParam(titleParam);
    }

    @GetMapping("/count")
    public Long getOffersNumber() {
        return offerService.getOffersNumber();
    }

    @PostMapping("")
    public ResponseEntity<OfferDto> saveNewOffer(@RequestBody OfferDto offerDto) {
        OfferDto dto = offerService.saveNew(offerDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Category> deleteOfferById(@PathVariable("id") Long id) {
        offerService.deleteOfferById(id);
        return ResponseEntity.ok().build();
    }
}
