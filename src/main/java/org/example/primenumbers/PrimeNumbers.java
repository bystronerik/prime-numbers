package org.example.primenumbers;

import org.example.primenumbers.command.RunCommand;
import picocli.CommandLine;

public class PrimeNumbers {

    public static void main(String[] args) {
        final CommandLine cmd = new CommandLine(new RunCommand());
        System.exit(cmd.execute(args));
    }

}
