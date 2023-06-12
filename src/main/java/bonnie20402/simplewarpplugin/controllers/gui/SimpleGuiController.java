package bonnie20402.simplewarpplugin.controllers.gui;

import bonnie20402.simplewarpplugin.controllers.gui.base.BaseGuiController;
import bonnie20402.simplewarpplugin.guiviews.base.BaseGuiView;
import org.bukkit.plugin.Plugin;


public class SimpleGuiController extends BaseGuiController {
    private final Plugin plugin;
    public SimpleGuiController(Plugin plugin,BaseGuiView guiView) {
        super(guiView);
        this.plugin = plugin;
    }
}
