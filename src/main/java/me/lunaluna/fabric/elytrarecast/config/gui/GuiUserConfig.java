package me.lunaluna.fabric.elytrarecast.config.gui;

import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import fi.dy.masa.malilib.gui.widgets.WidgetListConfigOptions;
import fi.dy.masa.malilib.util.StringUtils;
import me.lunaluna.fabric.elytrarecast.Reference;
import me.lunaluna.fabric.elytrarecast.config.UserConfig;

import java.util.List;

public class GuiUserConfig extends GuiConfigsBase {
    private static final String TITLE_KEY = Reference.MOD_ID + ".gui.title.config";
    static ConfigGuiTab current = ConfigGuiTab.RECASTING;
    public GuiUserConfig() {
        super(10, 50, Reference.MOD_ID, null, TITLE_KEY);
    }

    @Override
    public void initGui() {
        super.initGui();
        clearOptions();
        var x = 10;
        var y = 26;
        for (ConfigGuiTab tab : ConfigGuiTab.values()) { x += createButton(x, y, -1, tab); }
    }

    private int createButton(int x, int y, int width, ConfigGuiTab tab) {
        ButtonGeneric button = new ButtonGeneric(x, y, width, 20, tab.displayName());
        button.setEnabled(GuiUserConfig.current != tab);
        addButton(button, new ButtonListener(tab, this));
        return button.getWidth() + 2;
    }

    @Override
    public boolean useKeybindSearch() {
        return current == ConfigGuiTab.HOTKEYS;
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        if (current == ConfigGuiTab.RECASTING) return ConfigOptionWrapper.createFor(UserConfig.Recasting.OPTIONS);
        else if (current == ConfigGuiTab.JUMPING) return ConfigOptionWrapper.createFor(UserConfig.Jumping.OPTIONS);
        else return ConfigOptionWrapper.createFor(UserConfig.Hotkey.OPTIONS);
    }



    class ButtonListener implements IButtonActionListener {

        private final ConfigGuiTab tab;
        private final GuiUserConfig parent;

        ButtonListener(ConfigGuiTab tab, GuiUserConfig parent) {
            this.tab = tab;
            this.parent = parent;
        }

        @Override
        public void actionPerformedWithButton(ButtonBase button, int mouseButton) {
            GuiUserConfig.current = tab;
            parent.reCreateListWidget();
            WidgetListConfigOptions listWidget = parent.getListWidget();
            if(listWidget != null) listWidget.resetScrollbarPosition();
            parent.initGui();
        }
    }
}

enum ConfigGuiTab {
    RECASTING("$TITLE_KEY.recasting"),
    JUMPING("$TITLE_KEY.jumping"),
    HOTKEYS("$TITLE_KEY.hotkeys");

    ConfigGuiTab(String translationKey) {
        this.translationKey = translationKey;
    }

    private final String translationKey;

    String displayName() {
        return StringUtils.translate(translationKey);
    }
}