scoreboard players add #wallspawner wall_timer 1


execute if score #wallspawner wall_timer matches 1 run place structure agotmod:the_wall_top_middle_1 -240 96 0
execute if score #wallspawner wall_timer matches 5 run place structure agotmod:the_wall_top_middle_1 -192 96 0
execute if score #wallspawner wall_timer matches 10 run place structure agotmod:the_wall_top_middle_1 -144 96 0
execute if score #wallspawner wall_timer matches 15 run place structure agotmod:the_wall_top_middle_1 -96 96 0
execute if score #wallspawner wall_timer matches 20 run place structure agotmod:the_wall_top_middle_1 -48 96 0
execute if score #wallspawner wall_timer matches 25 run place structure agotmod:the_wall_top_middle_1 0 96 0
execute if score #wallspawner wall_timer matches 30 run place structure agotmod:the_wall_top_middle_1 48 96 0
execute if score #wallspawner wall_timer matches 35 run place structure agotmod:the_wall_top_middle_1 96 96 0
execute if score #wallspawner wall_timer matches 40 run place structure agotmod:the_wall_top_middle_1 144 96 0
execute if score #wallspawner wall_timer matches 45 run place structure agotmod:the_wall_top_middle_1 192 96 0
execute if score #wallspawner wall_timer matches 50 run place structure agotmod:the_wall_top_middle_1 240 96 0

execute if score #wallspawner wall_timer matches 50 run scoreboard players set #wallspawner wall_complete 1