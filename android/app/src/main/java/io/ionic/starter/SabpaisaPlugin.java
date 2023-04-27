package io.ionic.starter;

import androidx.annotation.Nullable;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.sabpaisa.gateway.android.sdk.SabPaisaGateway;
import com.sabpaisa.gateway.android.sdk.interfaces.IPaymentSuccessCallBack;
import com.sabpaisa.gateway.android.sdk.models.TransactionResponsesModel;

@CapacitorPlugin(name = "Sabpaisa")
public class SabpaisaPlugin extends Plugin implements IPaymentSuccessCallBack<TransactionResponsesModel> {

    PluginCall call;

    @PluginMethod()
    public void nativeSdkCall(PluginCall call) {
        this.call = call;
        String firstName = call.getString("firstName");
        String lastName = call.getString("lastName");
        String amount = call.getString("amount");
        String number = call.getString("number");
        String email = call.getString("email");
        openSdk(firstName,lastName,amount,number,email);
    }

    void openSdk(String firstName, String lastName, String amount, String number, String email) {
        SabPaisaGateway sabPaisaGateway1 =
                SabPaisaGateway.Companion.builder()
                        .setAmount(Double.parseDouble(amount))   //Mandatory Parameter
                        .setFirstName(firstName) //Mandatory Parameter
                        .setLastName(lastName) //Mandatory Parameter
                        .setMobileNumber(number) //Mandatory Parameter
                        .setEmailId(email)//Mandatory Parameter
                        .setClientCode("TM001")
                        .setAesApiIv("YN2v8qQcU3rGfA1y")
                        .setAesApiKey("kaY9AIhuJZNvKGp2")
                        .setTransUserName("rajiv.moti_336")
                        .setTransUserPassword("RIADA_SP336")
                        .build();
        SabPaisaGateway.Companion.setInitUrl("https://securepay.sabpaisa.in/SabPaisa/sabPaisaInit?v=1");
        SabPaisaGateway.Companion.setEndPointBaseUrl("https://securepay.sabpaisa.in");
        SabPaisaGateway.Companion.setTxnEnquiryEndpoint("https://txnenquiry.sabpaisa.in");

        sabPaisaGateway1.init(getActivity(), this);
    }

    @Override
    public void onPaymentFail(@Nullable TransactionResponsesModel transactionResponsesModel) {

        if (transactionResponsesModel != null){
            JSObject ret = new JSObject();
            ret.put("status", transactionResponsesModel.getStatus());
            ret.put("clientTxnId", transactionResponsesModel.getClientTxnId());
            call.resolve(ret);
        }

    }

    @Override
    public void onPaymentSuccess(@Nullable TransactionResponsesModel transactionResponsesModel) {
        if (transactionResponsesModel != null){
            JSObject ret = new JSObject();
            ret.put("status", transactionResponsesModel.getStatus());
            ret.put("clientTxnId", transactionResponsesModel.getClientTxnId());
            call.resolve(ret);
        }
    }


}
