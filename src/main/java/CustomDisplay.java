import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;

public abstract class CustomDisplay {

    private final ItemDisplay itemDisplay;
    private boolean isend = false;

    public CustomDisplay(Location loc, ItemStack i) {
        itemDisplay = (ItemDisplay) loc.getWorld().spawnEntity(loc, EntityType.ITEM_DISPLAY);
        itemDisplay.setItemStack(i);
        itemDisplay.setInterpolationDuration(1);
    }

    public void remove() {
        itemDisplay.remove();
    }

    public void setItem(ItemStack i) {
        itemDisplay.setItemStack(i);
    }

    public void teleport(Location loc) {
        itemDisplay.teleport(loc);
    }

    public void teleport(Entity en) {
        itemDisplay.teleport(en);
    }

    public void rotation(double roll) {
        double r = roll*Math.PI/360f;
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getLeftRotation().set(0f, 0f, (float) Math.sin(r), (float) Math.cos(r));
        itemDisplay.setTransformation(transformation);
    }

    public void scale(float x, float y, float z) {
        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(x, y, z);
        itemDisplay.setTransformation(transformation);
    }

    public boolean isEnd() {
        return isend;
    }

    public abstract void update();

}
