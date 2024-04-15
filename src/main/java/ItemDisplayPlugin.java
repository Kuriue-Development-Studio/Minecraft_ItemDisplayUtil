import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class ItemDisplayPlugin extends JavaPlugin {

    public static ItemDisplayPlugin plugin;
    private ArrayList<CustomDisplay> list = new ArrayList<>();
    private ArrayList<CustomDisplay> adder = new ArrayList<>();
    private boolean isupdate = false;

    public void addCustomDisplay(CustomDisplay display) {
        if(isupdate) {
            adder.add(display);
        }else {
            list.add(display);
        }
    }

    private void update() {
        BukkitRunnable brun = new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<CustomDisplay> removing = new ArrayList<>();
                isupdate = true;
                for(CustomDisplay display : list) {
                    display.update();
                    if(display.isEnd()) {
                        display.remove();
                        removing.add(display);
                    }
                }
                isupdate = false;
                list.removeAll(removing);
                list.addAll(adder);
                adder.clear();
            }
        };brun.runTaskTimer(this, 0, 0);
    }

    @Override
    public void onEnable() {
        plugin = this;
        update();
    }

    @Override
    public void onDisable() {
        for(CustomDisplay display : list) {
            display.remove();
        }
    }
}
