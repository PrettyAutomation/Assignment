package util;

public interface CommonConstants {

    String signIn = "(//a[@data-ux-jq-mouseenter = 'true'])[1]";
    String email = "//input[@id = 'ap_email']";
    String continuebutton = "continue";
    String password1 = "ap_password";
    String login = "signInSubmit";
    String searchBox = "twotabsearchtextbox";
    String item_text_to_search = "Watches for women";
    String searchIcon = "//input[@tabindex= '10']";
    String watchimagelist = "//img[@data-image-latency='s-product-image']";
    String quantity = "//select[@name= 'quantity']";
    String buyNow = "//input[@id= 'buy-now-button']";
    String upiMethod = "(//input[@name='ppw-instrumentRowSelection'])[4]";
    String upiId = "//input[@placeholder='Ex: MobileNumber@upi']";
    String verifyUpi = "//span[text()= 'Verify']";
    String UpiErrorMsg = "//div[@class = 'a-alert-content']";
    String verified = "//div[text()='Verified!']";
    String submitOrderButtonid = "(//input[@type = 'submit'])[4]";



}
