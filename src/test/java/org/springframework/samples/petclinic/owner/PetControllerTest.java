package org.springframework.samples.petclinic.owner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.WebDataBinder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

	@Mock
	private OwnerRepository ownerRepository;

	@InjectMocks
	private PetController petController;

	@BeforeEach
	void setUp() {
		petController = new PetController(ownerRepository);
	}

	@Test
	@DisplayName("Test populatePetTypes method")
	void testPopulatePetTypes() {
		// Arrange
		List<PetType> petTypes = Arrays.asList(new PetType(), new PetType());
		doReturn(petTypes).when(ownerRepository).findPetTypes();

		// Act
		Collection<PetType> result = petController.populatePetTypes();

		// Assert
		assertThat(result).isEqualTo(petTypes);
		verify(ownerRepository).findPetTypes();
	}

	@Test
	@DisplayName("Test findOwner method")
	void testFindOwner() {
		// Arrange
		Owner owner = new Owner();
		doReturn(owner).when(ownerRepository).findById(1);

		// Act
		Owner result = petController.findOwner(1);

		// Assert
		assertThat(result).isEqualTo(owner);
		verify(ownerRepository).findById(1);
	}

	@Test
	@DisplayName("Test initOwnerBinder method")
	void testInitOwnerBinder() {
		// Arrange
		WebDataBinder dataBinder = new WebDataBinder(null, "owner");

		// Act
		petController.initOwnerBinder(dataBinder);

		// Assert
		assertThat(Arrays.asList(dataBinder.getDisallowedFields())).contains("id");
	}

}
