package Steps;

import Interface.PetNavigationItemIdManager;
import Interface.PricePetManager;
import Pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Define los pasos de Cucumber para navegar, seleccionar productos, validar páginas, guardar precios, etc.

public class PetFeatureSteps {
    PageBoard pb = new PageBoard();

    //Objetos Product Id Page para cada tipo de mascota
    FishProductIdPage fishProductIdPage = new FishProductIdPage();
    DogsProductIdPage dogsProductIdP = new DogsProductIdPage();
    ReptilesProductIdPage reptilesProductIdP = new ReptilesProductIdPage();
    CatsProductIdPage catsProductIdP = new CatsProductIdPage();
    BirdsProductIdPage birdsProductIdP = new BirdsProductIdPage();

    //Objetos Item Id Page para cada tipo de mascota
    FishItemIdPage fishItemIdPage = new FishItemIdPage();
    DogsItemIdPage dogsItemIdPage = new DogsItemIdPage();
    ReptilesItemIdPage reptilesItemIdPage = new ReptilesItemIdPage();
    CatsItemIdPage catsItemIdPage = new CatsItemIdPage();
    BirdsItemIdPage birdsItemIdPage = new BirdsItemIdPage();

    private PricePetManager priceManager;
    private PetNavigationItemIdManager itemIdManager;
    private List<Double> savedPricesList = new ArrayList<>();
    private ShoppingCartPage cartPage = new ShoppingCartPage();

    @Given("Acceder a la pagina {string}")
    public void accessPage(String url) {
        pb.accessPageURL(url);
    }

    @Given("Validate that there is a superior menu for {string}")
    public void validateThatThereIsASuperiorMenuFor(String petType) {
        pb.getMenuName(petType);
    }

    @Given("Click on superior menu {string}")
    public void clickOnSuperiorMenu(String petName) {
        selectPet(petName);
    }

    private void selectPet(String petName) {
        pb.ClickOnMenuOption(petName);
        switch (petName) {
            case "Fish":
                priceManager = new FishItemIdPage();
                itemIdManager = new FishItemIdPage();
                break;
            case "Dogs":
                priceManager = new DogsItemIdPage();
                itemIdManager = new DogsItemIdPage();
                break;
            case "Reptiles":
                priceManager = new ReptilesItemIdPage();
                itemIdManager = new ReptilesItemIdPage();
                break;
            case "Cats":
                priceManager = new CatsItemIdPage();
                itemIdManager = new CatsItemIdPage();
                break;
            case "Birds":
                priceManager = new BirdsItemIdPage();
                itemIdManager = new BirdsItemIdPage();
                break;
            default:
                break;
        }
    }

    @Then("Validate page {string}")
    public void validatePage(String currentUrl) {
        pb.validateURL(currentUrl);
    }

    @When("Click on id product {string}")
    public void clickOnIdProduct(String petProdId) {
        switch (petProdId) {
            case "FI-SW-01":
                fishProductIdPage.seleccionarProductId(petProdId);
                break;
            case "K9-BD-01":
                dogsProductIdP.seleccionarProductId(petProdId);
                break;
            case "RP-SN-01":
                reptilesProductIdP.seleccionarProductId(petProdId);
                break;
            case "FL-DSH-01":
                catsProductIdP.seleccionarProductId(petProdId);
                break;
            case "AV-CB-01":
                birdsProductIdP.seleccionarProductId(petProdId);
                break;
            default:
                break;
        }
    }

    @And("El catalogo esta completamente cargado")
    public void elCatalogoEstaCompletamenteCargado() {
        pb.verifyCatalogLoaded();
    }

    @And("Click on Add to Cart Button")
    public void clickOnAddToCartButton() {
        fishItemIdPage.clickOnAddToCart();
    }

    @And("Back to main page")
    public void backToMainPage(String mainMenuURL) {
        pb.goToMainPage();
    }

}
