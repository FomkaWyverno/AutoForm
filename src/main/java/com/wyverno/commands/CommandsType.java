package com.wyverno.commands;

public enum CommandsType {
    BanOffline(BanOffline.class, "banoff"),
    JailOffline(JailOffline.class, "jailoff"),
    MuteOffline(MuteOffline.class, "muteoff"),
    WarnOffline(WarnOffline.class, "warnoff");

    private final Class<? extends Command> CLAZZ;
    private final String NAME_COMMAND;

    CommandsType(Class<? extends Command> clazz, String nameCommand) {
        this.CLAZZ = clazz;
        this.NAME_COMMAND = nameCommand;
    }
    public String getNAME_COMMAND() {
        return this.NAME_COMMAND;
    }

    public Class<? extends Command> getCLAZZ() {
        return this.CLAZZ;
    }
}

