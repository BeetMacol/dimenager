# Dimenager
Dimenager is a [Fabric](https://fabricmc.net/) server-side mod that lets you easily manage Minecraft dimensions in-game using simple commands.  
The mod **does not depend** on any other mods including the [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api).
## The concept
### Dimensions
Every dimension is represented with an identifier and contains its own generator settings and is linked to a dimension type. To avoid any confusion `/dimension worlds` is used to manage the dimensions, and is called "worlds" because `/dimension dimensions` would not sound too well.
### Dimension types
The dimension types are a collection of properties for your dimensions. I recommend using the `minecraft:overworld` dimension types in most cases. New dimension types will be created with overworld dimension settings, and can be modified later. For the properties list (with comments), see [this section of the ***Custom dimension*** article on the **Official Minecraft Wiki**](https://minecraft.gamepedia.com/Custom_dimension#Syntax). You can also find the settings of the vanilla dimension types there.
### Generators
The generators simply tell the game how to generate a dimension. Creating a world with a generator and then changing that generator won't change how the dimension generates, because the `/dimension worlds add ...` command creates the exact copy of the given generator. This is not the case in dimension types as the world is linked to a dimension type.  
### Generator types
Every generator is based on a generator type, and contains its properties. The types can only be added by Minecraft or mods. The vanilla generator types are: `minecraft:noise`, `minecraft:flat` and `minecraft:debug`, but the Dimenager also adds `dimenager:empty`, so you don't need to create flat worlds with one air layer (there is also a build-in generator that is based on that generator type).
## The `/dimension` command
Most of the mod's features can be used with the vanilla styled `/dimension` command. There is an alias for that command simply called `/dimenager` after the mod.  
This command splits iinto three sub commands: `/dimension worlds`, `/dimension types` and `/dimension generators`.
### Syntax tree
```
/dimension
├── worlds
│   ├──	add <identifier> <type> <generator>    Creates a new dimension
│   ├──	remove <dimension>                     Removes a dimension
│   └──	list                                   Lists dimensions
├── types
│   ├──	add <identifier>                       Creates a new dimension type with defualt settings
│   │	└── copy <other>                       Copies settings of a dimension type to a new one
│   ├──	remove <type>                          Removes a dimension type
│   ├──	list                                   Lists available dimension types
│   └──	set <property> <value>                 Changes a property of a dimension type
└── generators
    ├──	add <identifier>
    │   ├── <type>                             Creates a generator of given gnerator type
    │   └── copy <other>                       Creates a new generator from another one
    ├──	remove <generator>                     Removes a generator
    ├──	modify // TODO
    ├──	list                                   Lists available generators
    └──	types                                  Lists available generator types
/dimenager -> /dimension
```
## Changes in the `/tp` command
**You can disable this feature in the mod's configuration by setting the `modifyTpCommand` option to `false` and restarting the game.**  
The mod also modifies Minecraft's `/teleport` (aka `/tp`) command. It allows you to simply teleport to other dimensions using the `/tp` command instead of long `/execute` commands.  
### Examples  
| Vanilla | Dimenager  |
|---|---|
| `/execute in minecraft:the_nether run tp ~ ~ ~` | `/tp minecraft:the_nether` |
| `/execute in minecraft:the_end run tp 118 65 224` | `/tp minecraft:the_end 118 65 224` |
| `/execute in example:dimension1 run tp OtherPlayer ~ ~ ~` | `/tp OtherPlayer example:dimension1` |
