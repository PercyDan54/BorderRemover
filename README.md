# Border Remover
A Minecraft mod for removing the 30,000,000 block limit and (optionally) brings back the farlands
[![](https://z3.ax1x.com/2021/08/02/fpgDCq.png)](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
[![](https://z3.ax1x.com/2021/08/02/fpgr80.png)](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
![](https://z3.ax1x.com/2021/08/02/fpwnBt.png)
## Settings
`Enable farlands`: As its name saids

`Terrain Generation offset`: Offsets the `ChunkPos` used to generate terrain. Set to `0` to disable modifying.

Take the coordinate you need and divide it by 16 to get the ChunkPos

Some common values you may need:

`±784425` = Move farlands(if enabled) to (0,0) in 1.17

`±100406586` = Move farlands(if enabled) to (0,0) in 1.18

`±62754114` = Move farther lands(if enabled) to (0,0)

`Y / XZ Scale`: Modifies the Y / XZ coordinate scale multiplier. Set to `default` to disable modifying.

Set `Y Scale` to `262144` to get the sky far lands(if enabled)
