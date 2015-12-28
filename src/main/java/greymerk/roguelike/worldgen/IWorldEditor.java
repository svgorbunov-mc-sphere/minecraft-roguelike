package greymerk.roguelike.worldgen;

import java.util.List;
import java.util.Random;

import greymerk.roguelike.treasure.ITreasureChest;
import greymerk.roguelike.treasure.TreasureManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;

public interface IWorldEditor {

	boolean setBlock(Coord pos, IBlockState state, int flag, boolean fillAir, boolean replaceSolid);

	boolean setBlock(Coord cursor, MetaBlock metaBlock);
	
	boolean setBlock(Random rand, Coord pos, IBlockFactory fillOne, boolean fillAir, boolean replaceSolid);

	void fillRectHollow(Random rand, Coord start, Coord end, IBlockFactory blocks, boolean fillAir, boolean replaceSolid);

	void fillRectHollow(Random rand, Coord start, Coord end, IBlockFactory blocks);
	
	void fillRectSolid(Random rand, Coord start, Coord end, IBlockFactory blocks, boolean fillAir, boolean replaceSolid);

	void fillRectSolid(Random rand, Coord start, Coord end, IBlockFactory blocks);
	
	List<Coord> getRectHollow(Coord start, Coord end);

	List<Coord> getRectSolid(Coord start, Coord end);
	
	MetaBlock getBlock(Coord cursor);

	boolean isAirBlock(Coord coord);
	
	TileEntity getTileEntity(Coord pos);

	BiomeGenBase getBiome(Coord coord);
	
	int getDimension();
	
	long getSeed();
	
	Random getSeededRandom(int m, int n, int i);
	
	void fillDown(Random rand, Coord pos, IBlockFactory pillar);
	
	boolean canPlace(MetaBlock block, Coord pos, Cardinal dir);
	
	boolean validGroundBlock(Coord cursor);

	void spiralStairStep(Random rand, Coord c, IStair stair, IBlockFactory pillar);

	int getStat(Block stonebrick);

	TreasureManager getTreasure();

	void addChest(ITreasureChest chest);

}