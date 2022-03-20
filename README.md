# ForgetMeChunk

ForgetMeChunk is a simple client side mod that removes a type of light update when unloading chunks.
This fixes the large lag spikes you sometimes get when crossing a chunk border.

It is unlikely that this would cause any issues, however any issues it does cause would be client side only and fixed by reloading the affected chunks.

However use at your own risk.

# A note on Starlight

Starlight (and other lighting optimization mods) do work with ForgetMeChunk.

Starlight optimizes the lighting engine to be able to compute lighting updates much faster.
However, these lighting updates can still take a large amount of time, this depends on your system & world.
ForgetMeChunk cancels a lighting update that is (seemingly) useless, meaning it is never computed in the first place. 
Removing not needed calculations is better than doing those calculations quickly.
So while Starlight & ForgetMeChunk seem to work on the same issue (lighting being slow), they do this in different ways.
Meaning there can be a benefit to using both mods at the same time it just depends on your world & computer.

TL;DR no these mods don't do the same thing & yes they work together.