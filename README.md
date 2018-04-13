# Hestia

In Ancient Greek religion, Hestia (/ˈhɛstiə/; Greek: Ἑστία, "hearth" or "fireside") is a virgin goddess of the hearth, architecture, and the right ordering of domesticity, the family, the home, and the state.

So, Hestia is the project which controlls the devices in home smartly. Additionally, It also supports some extended functionality which is described in the [Feature (Brain Part)](#feature-brain-part).

![hestia structure](https://docs.google.com/drawings/d/e/2PACX-1vT8G32e2AJbFTea1f_9dLB_ZKbS85kUqXnLZNPJ8lp0TAohMYc4bMiz-4qDYa-DdlcfmkpeNlxP81B6/pub?w=724&h=306)

## Hestia Server
This is the server, based Java language, for listening to clients, disposing the data (<b>Brain Part</b>), controlling the home device, responsing infomation and so on.

![hestia server structure](https://docs.google.com/drawings/d/e/2PACX-1vSCb37rXZ-ngSvVRu3TqxvlO3cdOPEyslglEa-SK8WoeEmHpSSwC1bdy0W4ido_F_uG5PBejP_WOp7G/pub?w=961&h=282)


## Other Clients

[Hestia Android](https://github.com/GenialX/hestia-android)

[Hestia iOS(not maintained)](https://github.com/GenialX/hestia-ios)

[Hestia Raspberry Pi](https://github.com/GenialX/hestia-rpi)

## Feature (Brain Part)

The hestia-server's Brain Part consists of several strategy classes which are in the com.ihuxu.hestia.server.library.brain package.

### BrainLocationStrategy

- Send your current location which is from your Mobile Device to the Hestia Server

- Trigger BACK HOME and LEAVE HOME event according the location from the mobile device
  - BACK HOME
    when back home, it would turn on the light according to some factors, such the brightness in the room, the switch status of the yeelight
  - LEAVE HOME
    when leave home, it would turn off the light according to some factors, such the brightness in the room, the switch status of the yeelight
