package RoomDB;

public interface RoomAdapterListener {
    // parameter from id -> roomActivity, position coming from adapter respectively.
    void OnUpdate(int id, int position);
    void OnDelete(int id, int position);
}
