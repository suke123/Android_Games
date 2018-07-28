package jtp.c.dendai.ac.jp.adventure.scene;
public enum GameState {
    first(new First()),
    second(new Second()),
    second2(new Second2()),

    aniD1(new AniD1()),
    aniD2(new AniD2()),
    aniD3(new AniD3()),
    aniD4(new AniD4()),
    aniD5(new AniD5()),
    aniD6(new AniD6()),
    aniD7(new AniD7()),
    aniD8(new AniD8()),
    aniD9(new AniD9()),


    fre1(new Fre1()),
    aniX1(new AniX1()),
    aniX2(new AniX2()),
    aniX3(new AniX3()),
    aniX4(new AniX4()),
    aniX5(new AniX5()),
    aniX6(new AniX6()),
    aniX7(new AniX7()),
    aniX8(new AniX8()),


    aniY1(new AniY1()),
    aniY2(new AniY2()),
    aniY3(new AniY3()),
    aniY4(new AniY4()),
    aniY5(new AniY5()),
    aniY6(new AniY6()),
    aniY7(new AniY7()),
    aniY8(new AniY8()),

    aniZ1(new AniZ1()),
    aniZ2(new AniZ2()),
    aniZ3(new AniZ3()),
    aniZ4(new AniZ4()),
    aniZ5(new AniZ5()),
    aniZ6(new AniZ6()),
    aniZ7(new AniZ7()),
    aniZ8(new AniZ8()),

    ending(new Ending()),
    badend(new BadEnd()),
    deadend(new DeadEnd());

    private Scene scene;
    public Scene getScene(){
        return scene;
    }
    private GameState(Scene s){
        scene = s;
    }
    public static Scene getInitialScene(){
        return first.getScene();
    }
}