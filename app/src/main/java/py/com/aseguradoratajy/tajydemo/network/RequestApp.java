package py.com.aseguradoratajy.tajydemo.network;

import org.json.JSONObject;

import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.utils.JsonObjectRequest;


/**
 * Created by diego on 18/10/16.
 */

public abstract class RequestApp {

    protected ProgressDialogFragment progressDialog;
    protected JsonObjectRequest jsonObjectRequest;


    protected abstract void handleResponse(JSONObject response);

    protected abstract void confirm();

    protected abstract void execute();

    public abstract class RequestObject {
        public abstract JSONObject getParams();
    }

    public JsonObjectRequest getMomoJsonObjectRequest() {
        return jsonObjectRequest;
    }

}
