package pl.mvwojcik.allergen;

import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.mvwojcik.allergen.data.Allergen;
import pl.mvwojcik.allergen.data.AllergenDTO;
import pl.mvwojcik.allergen.data.AllergenMapper;
import pl.mvwojcik.allergen.data.AllergensRepository;
import pl.mvwojcik.comunication.ContentResponse;
import pl.mvwojcik.comunication.EmptyResponse;
import pl.mvwojcik.comunication.ServiceResponse;
import pl.mvwojcik.error.ErrorConstants;
import pl.mvwojcik.error.ErrorResponse;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public final class AllergensService {

    private final AllergensRepository repository;
    private final AllergenValidator validator;

    @Autowired
    public AllergensService(AllergensRepository repository, AllergenValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public final ServiceResponse createAllergen(AllergenDTO allergenDTO) {
        return validator.runAllergenValidation(allergenDTO)
                .toEither()
                .flatMap(this::save)
                .map(this::prepareResponse)
                .fold(Function.identity(), Function.identity());
    }

    public final Either<ErrorResponse, Allergen> save(AllergenDTO allergenDTO) {
        return Either.right(this.repository.save(AllergenMapper.mapAllergenDTOToAllergen(allergenDTO)));
    }

    public final ServiceResponse getAll(int page) {
        return new ContentResponse<>(HttpStatus.OK,repository
                .findAll(PageRequest.of(page,10))
                .map(AllergenMapper::mapAllergenToAllergenDTO));
    }

    public final ServiceResponse deleteAllergen(Long id) {
        return Option.ofOptional(repository.findById(id))
                .toEither(ErrorConstants.allergenNotFound(id.toString()))
                .map(this::deleteAllergen)
                .fold(Function.identity(), a -> new EmptyResponse(HttpStatus.NO_CONTENT));
    }

    public ServiceResponse interactiveSearch(String word) {
        Set<AllergenDTO> list = repository.findByNameContaining(word)
                .stream()
                .map(AllergenMapper::mapAllergenToAllergenDTO)
                .collect(Collectors.toSet());
        return new ContentResponse<>(HttpStatus.OK,list);
    }
    public final Either<ErrorResponse, Allergen> deleteAllergen(Allergen allergen) {
        repository.delete(allergen);
        return Either.right(allergen);
    }

    private ContentResponse<AllergenDTO> prepareResponse(Allergen allergen) {
        AllergenDTO dto = AllergenMapper.
                mapAllergenToAllergenDTO(allergen);
        return new ContentResponse<>(HttpStatus.CREATED, dto);
    }

}
