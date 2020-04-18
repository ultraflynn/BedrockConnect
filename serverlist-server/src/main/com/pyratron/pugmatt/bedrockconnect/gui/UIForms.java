package com.pyratron.pugmatt.bedrockconnect.gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nukkitx.protocol.bedrock.packet.ModalFormRequestPacket;

import java.util.List;

public class UIForms {
    public static final int ERROR = 2, MAIN = 0, DIRECT_CONNECT = 1, REMOVE_SERVER = 3;

    public static int currentForm = 0;

    public static ModalFormRequestPacket createMain(List<String> servers) {
        currentForm = MAIN;
        ModalFormRequestPacket mf = new ModalFormRequestPacket();
        mf.setFormId(UIForms.MAIN);

        JsonObject out = UIComponents.createForm("form", "Server List");
        out.addProperty("content", "");

        JsonArray buttons = new JsonArray();

        buttons.add(UIComponents.createButton("Connect to a Server"));
        buttons.add(UIComponents.createButton("Remove a Server"));
        for (int i = 0; i < servers.size(); i++) {
            buttons.add(UIComponents.createButton(servers.get(i), "https://i.imgur.com/3BmFZRE.png", "url"));
        }
        buttons.add(UIComponents.createButton("The Hive", "https://forum.playhive.com/uploads/default/original/1X/0d05e3240037f7592a0f16b11b57c08eba76f19c.png", "url"));
        buttons.add(UIComponents.createButton("Mineplex", "https://www.mineplex.com/assets/www-mp/img/footer/footer_smalllogo.png", "url"));
        buttons.add(UIComponents.createButton("CubeCraft Games", "https://i.imgur.com/aFH1NUr.png", "url"));
        buttons.add(UIComponents.createButton("Lifeboat Network", "https://lbsg.net/wp-content/uploads/2017/06/lifeboat-square.png", "url"));
        buttons.add(UIComponents.createButton("Mineville City", "https://pbs.twimg.com/profile_images/1095835578451537920/0-x9qcw8.png", "url"));
        out.add("buttons", buttons);

        mf.setFormData(out.toString());

        return mf;
    }

    public static ModalFormRequestPacket createDirectConnect() {
        currentForm = DIRECT_CONNECT;
        ModalFormRequestPacket mf = new ModalFormRequestPacket();
        mf.setFormId(UIForms.DIRECT_CONNECT);
        JsonObject out = UIComponents.createForm("custom_form", "Connect to a Server");

        JsonArray inputs = new JsonArray();

        inputs.add(UIComponents.createInput("Server Address", "Please enter IP or Address"));
        inputs.add(UIComponents.createInput("Server Port", "Please enter Port", "19132"));
        inputs.add(UIComponents.createToggle("Add to server list"));

        out.add("content", inputs);
        mf.setFormData(out.toString());

        return mf;
    }

    public static ModalFormRequestPacket createRemoveServer(List<String> servers) {
        currentForm = REMOVE_SERVER;
        ModalFormRequestPacket mf = new ModalFormRequestPacket();
        mf.setFormId(UIForms.REMOVE_SERVER);
        JsonObject out = UIComponents.createForm("custom_form", "Remove Server");

        JsonArray inputs = new JsonArray();

        inputs.add(UIComponents.createDropdown(servers, "Servers", "0"));

        out.add("content", inputs);
        mf.setFormData(out.toString());

        return mf;
    }

    public static ModalFormRequestPacket createError(String text) {
        currentForm = ERROR;
        ModalFormRequestPacket mf = new ModalFormRequestPacket();
        mf.setFormId(UIForms.ERROR);
        JsonObject form = new JsonObject();
        form.addProperty("type", "custom_form");
        form.addProperty("title", "Error");
        JsonArray content = new JsonArray();
        content.add(UIComponents.createLabel(text));
        form.add("content", content);
        mf.setFormData(form.toString());
        return mf;
    }
}
