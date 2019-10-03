package contracts;

public interface Command {
    void Action(Object... objects);

    void Reverse(Object... objects);
}
