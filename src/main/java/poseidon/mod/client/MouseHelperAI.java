package poseidon.mod.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.util.MouseHelper;

public class MouseHelperAI extends MouseHelper {
	
    public MouseHelperAI() {
        super();
        // DEBUG
        System.out.println("Constructing MouseHelper for AI bots");
    }
    
    @Override
    public void mouseXYChange() {
        if (Keyboard.isKeyDown(Keyboard.KEY_COMMA))
        {
            this.deltaX += 1;
        }
        else
        {
            this.deltaX = Mouse.getDX();
        }
        this.deltaY = Mouse.getDY();
    }
}

