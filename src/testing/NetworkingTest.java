package testing;

import model.Rummikub;
import model.RummikubPlayer;
import networking.Client;

/*
 * Testapplication for testing the classes in the networking package based on commands to be typed
 * into the commandline Arguments in the commandline are seperated by ":". Examples: create host:
 * c:44:tom, join a game: j:localhost:32:fritz or j:192.168.1.27:55:tim, leave a game: l, get a
 * backup: b, terminate server: t
 *
 */
public class NetworkingTest {

  // master is the client, who has the server
  static boolean isMaster = false;
  static Command command = Command.TERMINATE;
  static int paramCount = 0;
  static Rummikub game = null;
  static RummikubPlayer player = null;
  static Client client = null;

  private static Command getCommand(String input) {
    for (Command c : Command.values()) {
      if (input.toUpperCase().equals(c.value)) {
        return c;
      }
    }
    throw new IllegalArgumentException("The command " + input + " is not known.");
  }

  private static void checkParamCount(int count) {
    if (count != paramCount) {
      throw new IllegalArgumentException(
          "The command " + command.toString() + " needs " + count + " parameter(s).");
    }
  }

  private static int isPositiveNumber(String number) {
    int ret;
    try {
      ret = Integer.parseInt(number);
      // valid number, but may not be negative
      if (ret < 0) {
        throw new IllegalArgumentException("Negative number " + number + " not allowed.");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Argument " + number + " is no number");
    }
    return ret;
  }

  enum Command {
    JOINGAME("J"), CREATEGAME("C"), STARTGAME("S"), LEAVEGAME("L"), TERMINATE("T"), RESTART(
        "R"), BACKUPGAME("B");
    String value;

    Command(String value) {
      this.value = value;
    }
  }

}


