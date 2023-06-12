package bonnie20402.simplewarpplugin.controllers.gui;
import bonnie20402.simplewarpplugin.controllers.gui.base.BasePlayerGuiController;
import bonnie20402.simplewarpplugin.guiviews.base.BasePlayerGuiView;
import org.bukkit.plugin.Plugin;


public final class SimplePlayerGuiController extends BasePlayerGuiController {
    private final Plugin plugin;

    public SimplePlayerGuiController(Plugin plugin,BasePlayerGuiView basePlayerGuiView) {
        super(basePlayerGuiView);
        this.plugin = plugin;
    }
}
