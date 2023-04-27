package nmd.autoplant;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoPlant implements ModInitializer {
    public static final String ID = "autoplant";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    @Override
    public void onInitialize() {
        System.out.println("The Green to quietly reclaim the world");
    }
}
