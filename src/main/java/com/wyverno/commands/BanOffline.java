package com.wyverno.commands;

import com.wyverno.commands.annotations.ConstructorForCreateCommand;
import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.commands.annotations.NameParameter;

public class BanOffline extends Command {

    private static final String COMMAND = "/banoff";

    @FillableParameter
    public int typeBan;
    @FillableParameter
    public int days;

    @ConstructorForCreateCommand
    public BanOffline(@NameParameter(name = "nickname") String nickname,
                      @NameParameter(name = "reason") String reason,
                      @NameParameter(name = "prefix") String prefix,
                      @NameParameter(name = "typeBan") int typeBan,
                      @NameParameter(name = "days") int days) {
        super(nickname, reason, prefix);
        this.typeBan = typeBan;
        this.days = days;
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + this.typeBan + " " + this.days + " " + this.getReasonAndPrefix();
    }
}
