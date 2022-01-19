package me.lunaluna.fabric.elytrarecast.config.gui

import fi.dy.masa.malilib.gui.GuiConfigsBase
import fi.dy.masa.malilib.gui.button.ButtonBase
import fi.dy.masa.malilib.gui.button.ButtonGeneric
import fi.dy.masa.malilib.gui.button.IButtonActionListener
import fi.dy.masa.malilib.util.StringUtils
import me.lunaluna.fabric.elytrarecast.Reference
import me.lunaluna.fabric.elytrarecast.config.UserConfig

const val TITLE_KEY = "${Reference.MOD_ID}.gui.title.config"

class GuiUserConfig : GuiConfigsBase(10, 50, Reference.MOD_ID, null, TITLE_KEY) {
    companion object {
        private var tab = ConfigGuiTab.RECASTING
    }

    override fun initGui() {
        super.initGui()
        clearOptions()
        var x = 10
        val y = 26
        for (tab in ConfigGuiTab.values()) { x += createButton(x, y, -1, tab) }
    }

    private fun createButton(x: Int, y: Int, width: Int, tab: ConfigGuiTab): Int {
        val button = ButtonGeneric(x, y, width, 20, tab.displayName)
        button.setEnabled(GuiUserConfig.tab !== tab)
        addButton(button, ButtonListener(tab, this))
        return button.width + 2
    }

    override fun useKeybindSearch() = tab === ConfigGuiTab.HOTKEYS

    override fun getConfigs(): List<ConfigOptionWrapper?>? {
        val configs = when (tab) {
            ConfigGuiTab.RECASTING -> UserConfig.Recasting.OPTIONS
            ConfigGuiTab.JUMPING -> UserConfig.Jumping.OPTIONS
            ConfigGuiTab.HOTKEYS -> UserConfig.Hotkey.OPTIONS
        }
        return ConfigOptionWrapper.createFor(configs)
    }

    private class ButtonListener(private val tab: ConfigGuiTab, private val parent: GuiUserConfig) : IButtonActionListener {
        override fun actionPerformedWithButton(button: ButtonBase, mouseButton: Int) {
            GuiUserConfig.tab = tab
            parent.reCreateListWidget()
            parent.listWidget?.resetScrollbarPosition()
            parent.initGui()
        }
    }

    enum class ConfigGuiTab(private val translationKey: String) {
        RECASTING("$TITLE_KEY.recasting"),
        JUMPING("$TITLE_KEY.jumping"),
        HOTKEYS("$TITLE_KEY.hotkeys");

        val displayName: String get() = StringUtils.translate(translationKey)
    }
}