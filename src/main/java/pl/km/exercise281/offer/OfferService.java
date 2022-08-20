package pl.km.exercise281.offer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.km.exercise281.category.Category;
import pl.km.exercise281.category.CategoryRepository;
import pl.km.exercise281.exceptions.EntityNotFoundException;
import pl.km.exercise281.exceptions.InvalidDataInputException;
import java.util.List;
import java.util.Optional;

@Service
class OfferService {
    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    public OfferDto saveNew(OfferDto offerDto) {
        if (offerDto.getId() != null) {
            throw new InvalidDataInputException("Offer should not contain \"id\" field");
        }
        Offer offer = new Offer();
        fillFields(offer, offerDto);
        save(offer);
        return offerDto;
    }

    public Optional<Offer> findById(Long id) {
        return offerRepository.findById(id);
    }

    public Long getOffersNumber() {
        return findAll().stream().count();
    }

    public List<OfferDto> getOfferDtoListWithOptionalTitleParam(String titleParam) {
        if (titleParam == null) {
            return getAllOfferDto();
        }
        return offerRepository.findOfferByTitleContainingIgnoreCase(titleParam)
                .stream()
                .map(this::createNewOfferDtoFromOffer)
                .toList();
    }

    public void deleteOfferById(Long id) {
        try {
            offerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Offer with introduced \"id\" not found");
        }
    }

    private Offer fillFields(Offer offer, OfferDto offerDto) {
        offer.setId(offerDto.getId());
        offer.setTitle(offerDto.getTitle());
        offer.setDescription(offerDto.getDescription());
        offer.setImgUrl(offerDto.getImgUrl());
        offer.setPrice(offerDto.getPrice());
        Optional<Category> offerCategoryOptional = categoryRepository.findByNameEquals(offerDto.getCategory());
        if (offerCategoryOptional.isEmpty()) {
            throw new EntityNotFoundException("No such category found");
        }
        offer.setCategory(offerCategoryOptional.get());
        return offer;
    }

    public OfferDto getOfferById(Long id) {
        Optional<Offer> offerOptional = findById(id);
        if (offerOptional.isEmpty()) {
            throw new EntityNotFoundException("Offer with introduced \"id\" not found");
        }
        return createNewOfferDtoFromOffer(offerOptional.get());
    }

    private OfferDto createNewOfferDtoFromOffer(Offer offer) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offer.getId());
        offerDto.setTitle(offer.getTitle());
        offerDto.setDescription(offer.getDescription());
        offerDto.setImgUrl(offer.getImgUrl());
        offerDto.setPrice(offer.getPrice());
        offerDto.setCategory(offer.getCategory().getName());
        return offerDto;
    }

    private List<Offer> findAll() {
        return offerRepository.findAll();
    }

    private List<OfferDto> getAllOfferDto() {
        return findAll().stream()
                .map(this::createNewOfferDtoFromOffer)
                .toList();
    }
}
