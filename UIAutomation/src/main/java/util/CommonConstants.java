package util;

public interface CommonConstants {

    String popular = "//a[text()= 'Popular']";
    String Items = "//*[@id=\"homefeatured\"]/li";
    String price1 = "//*[@id='homefeatured']/li[";
    String originalPriceSecondPart = "]/div/div[2]/div[1]/span[2]";
    String discountPercentSecondPart = "]/div/div[2]/div[1]/span[3]";
    String actualPriceSecondPart = "]/div/div[2]/div[1]/span[1]";
    String itemSecondPart = "]/div/div[2]/div[1]/span";
    String AddToCart = "//span[text()='Add to cart']";
    String closeWindow = "//span[@title ='Close window']";
    String viewShoppingCart = "//a[@title ='View my shopping cart']";
    String emptyCart = "//span[contains(text(), '(empty)')]";


}
