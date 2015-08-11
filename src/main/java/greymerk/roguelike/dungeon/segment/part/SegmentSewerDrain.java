package greymerk.roguelike.dungeon.segment.part;

import java.util.Random;

import greymerk.roguelike.dungeon.IDungeonLevel;
import greymerk.roguelike.theme.ITheme;
import greymerk.roguelike.worldgen.Cardinal;
import greymerk.roguelike.worldgen.Coord;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.WorldEditor;
import net.minecraft.init.Blocks;

public class SegmentSewerDrain extends SegmentBase {

	
	@Override
	protected void genWall(WorldEditor editor, Random rand, IDungeonLevel level, Cardinal dir, ITheme theme, int x, int y, int z) {
		
		MetaBlock air = new MetaBlock(Blocks.air);
		MetaBlock water = new MetaBlock(Blocks.flowing_water);
		MetaBlock stair = theme.getSecondaryStair();
		MetaBlock bars = new MetaBlock(Blocks.iron_bars);
		
		Coord cursor;
		Coord start;
		Coord end;
		
		Cardinal[] orth = Cardinal.getOrthogonal(dir);
		
		start = new Coord(x, y, z);
		start.add(Cardinal.DOWN);
		end = new Coord(start);
		start.add(orth[0]);
		end.add(orth[1]);
		editor.fillRectSolid(rand, start, end, air, true, true);
		start.add(Cardinal.DOWN);
		end.add(Cardinal.DOWN);
		editor.fillRectSolid(rand, start, end, water, false, true);
				
		start = new Coord(x, y, z);
		start.add(dir, 2);
		end = new Coord(start);
		start.add(orth[0]);
		end.add(orth[1]);
		end.add(Cardinal.UP, 2);
		editor.fillRectSolid(rand, start, end, air, true, true);
		start.add(dir);
		end.add(dir);
		editor.fillRectSolid(rand, start, end, new MetaBlock(Blocks.mossy_cobblestone), true, true);
		
		for(Cardinal o : orth){
			cursor = new Coord(x, y, z);
			cursor.add(dir, 2);
			cursor.add(o);
			WorldEditor.blockOrientation(stair, Cardinal.reverse(o), false).setBlock(editor, cursor);
			cursor.add(Cardinal.UP);
			bars.setBlock(editor, cursor);
			cursor.add(Cardinal.UP);
			WorldEditor.blockOrientation(stair, Cardinal.reverse(o), true).setBlock(editor, cursor);
		}
		
		start = new Coord(x, y, z);
		start.add(Cardinal.UP);
		end = new Coord(start);
		end.add(dir, 5);
		editor.fillRectSolid(rand, start, end, air, true, true);
		water.setBlock(editor, end);
		
		cursor = new Coord(x, y, z);
		cursor.add(Cardinal.DOWN);
		cursor.add(dir);
		air.setBlock(editor, cursor);
		
	}
}