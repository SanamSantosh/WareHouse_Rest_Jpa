package RestHibernate.WareHouseManagement;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Repository.MerchantDetailsRepository;
import bean.MerchantStock;
import bean.PurchaseDetails;

@Path("merchant-details-page")
public class MerchantDetailsResource {
	
	//this method is for calling repository for creating a new merchant in db
	@POST
    @Path("create-merchant-stock")
    @Produces(MediaType.APPLICATION_JSON)
    public MerchantStock createMerchantStock(MerchantStock merchantStockObj)
    {
    	MerchantDetailsRepository repo=new MerchantDetailsRepository();
    	MerchantStock merchantObj=repo.addMerchantStock(merchantStockObj);
		return merchantObj;
	
    }
	
	//this method is for calling repository to reduce stock from the merchant table 
	// after merchant accepts the order
	@POST
    @Path("reduce-stock")
    @Produces(MediaType.APPLICATION_JSON)
    public PurchaseDetails Stock(PurchaseDetails purchaseObj)
    {
		PurchaseDetails removePurchase=new PurchaseDetails();
		MerchantDetailsRepository repo=new MerchantDetailsRepository();
		PurchaseDetails detailsObj=repo.reduceStock(purchaseObj);
		removePurchase.setMerchant_id(purchaseObj.getMerchant_id());
		removePurchase.setMerchantObj(purchaseObj.getMerchantObj());
		removePurchase.setPurchase_id(purchaseObj.getPurchase_id());
		removePurchase.setQuantity(purchaseObj.getQuantity());
		String reply=repo.removePurchase(removePurchase);
		if (reply.equals("Yes"))
		return detailsObj;
		else
			return detailsObj;	
    	
    }
	
	//this method is for calling repository for removing the order from the order table 
	//after merchant accepts/rejects order
	@POST
    @Path("remove-purchase")
    @Produces(MediaType.TEXT_PLAIN)
    public String details(PurchaseDetails purchaseObj)
    {
		PurchaseDetails removePurchase=new PurchaseDetails();
		MerchantDetailsRepository repo=new MerchantDetailsRepository();
		removePurchase.setMerchant_id(purchaseObj.getMerchant_id());
		removePurchase.setMerchantObj(purchaseObj.getMerchantObj());
		removePurchase.setPurchase_id(purchaseObj.getPurchase_id());
		removePurchase.setQuantity(purchaseObj.getQuantity());
		String reply=repo.removePurchase(removePurchase);
		if (reply.equals("Yes"))
		return reply="Order Rejected";	
		else
		return reply;
    	
    }
	

}
