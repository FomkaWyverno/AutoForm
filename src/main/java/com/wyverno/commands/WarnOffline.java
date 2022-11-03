package com.wyverno.commands;

public class WarnOffline extends Command {

    private static final String COMMAND = "/warnoff";

    public WarnOffline(String nickname, String reason, String prefix) {
        super(nickname, reason, prefix);
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + getReasonAndPrefix();
    }
}
