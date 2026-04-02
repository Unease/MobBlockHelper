# MobBlockHelper
This mod was only developed to assist in the aid of the creation of certain TASes in 1.12.2 and 1.11.2 as at the time of writing RNG has not been fully fixed. This is why many things are hard-coded for the sake of getting stuff to work rather than make it customizable. 

If you need to tweak certain things for your own TAS (until KilltheRNG is stable), you should learn how to create [Mixins](https://wiki.fabricmc.net/tutorial:mixin_introduction) as that is what consists the most of this mod.

What does this mod change?
Since this mod is intended to be used for a glitchless TAS things that are **NOT** implemented are:
- Item Duplication
- Player always invulnerable

Other miscellaneous things that are not implemented are:
- Manipulate Spawning
- Manipulate AI
- Dragon phase change

Changes moved from LoTAS

- Items will drop towards the player (Can be forced to drop away by multiplying item velocity by -1) (from LoTAS)
- Blocks will always drop when TNT explode (from LoTAS)
- Throwable Projectile Inaccuracy (from LoTAS)
- Unbreaking enchantment will never use durability (from LoTAS)

Changes added
- Fully charged bows are always critical hits
- The Elder Guardian never inflicts mining fatigue on the player (See the MixinEntityElderGuardian file)
- Creepers will always drop the max amount of gunpowder
- Blazes will always drop the max amount of rods
- Wither Skeletons will always drop skulls and nothing else

Blocks
- Gravel always drops flint
- Crops always drop the maximum amount
- DeadBushes always drop 2 sticks

# Installation
This mod requires [TASmod](https://github.com/MinecraftTAS/TASmod) to be installed to properly work. It also requires an instance created using [Orinthe CLI](https://ornithemc.net/download/). In order to create the instance, download the program and execute it from the command line with the argument `prism --minecraft-version VERSION --loader-type fabric --gen 2`. The `--loader-type fabric` and `--gen 2` arguments are **required** for both TASmod and this mod to work.

# Development Setup
This mod was developed using the [Discombobulator](https://github.com/MinecraftTAS/Discombobulator) Preprocessor. For more information, 
check the [wiki](https://github.com/MinecraftTAS/Discombobulator/wiki)

# Credits
The files in the [patches](https://github.com/Unease/MobBlockHelper/tree/develop/src/main/java/com/minecrafttas/mobblockhelper/mixin/patches) folder are all reused from [LoTAS](https://github.com/MinecraftTAS/LoTAS) and so should be credited it to original developers of Pancake, CittyKat, and Scribble.