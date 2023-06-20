package bonnie20402.simplespigotplugin.models.arena.enums;


public enum ArenaState {
    ARENA_STATE_LOADING,
    ARENA_STATE_WAITING,
    ARENA_STATE_STARTING,
    ARENA_STATE_FIGHTING,
    ARENA_STATE_FINISHED;

    public static String toString(ArenaState arenaState) {
        switch(arenaState) {
            case ARENA_STATE_FIGHTING -> {
                return "Fighting";
            }
            case ARENA_STATE_LOADING -> {
                return "Loading";
            }
            case ARENA_STATE_WAITING -> {
                return "Waiting";
            }
            case ARENA_STATE_FINISHED -> {
                return "Finished";
            }
        }
        return "???";
    }
}
