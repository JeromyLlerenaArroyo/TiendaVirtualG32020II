package tienda.models;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class BlockChainPaymentMethod extends PaymentMethod{

    private String walletId;

    @Override
    public  void payOrder(Order order){
        walletPayOrder(order);
    }

    public void walletPayOrder(Order order){
        Double price = order.calculateTotalOrder();
        /* Doing Blok Chain Validation */
        System.out.println("Processing payment with wallet "+walletId+" | total: "+price);
    }

    public String getWalletId() {
        if(this.walletId == null){
            UUID uuid = Generators.timeBasedGenerator().generate();
            setWalletId(uuid.toString());
        }
        return walletId;
    }

    public void setWalletId(String walletId){
        this.walletId = walletId;
    }
}
