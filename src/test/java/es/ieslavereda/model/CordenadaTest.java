package es.ieslavereda.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CordenadaTest {

    @BeforeAll
    static void initialize(){
        System.out.println("Ejecutado beforeALL");
    }

    @BeforeEach
    void before(){
        System.out.println("Ejecutado beforeEach");
    }

    @Test
    @DisplayName("Prueba del metodo up")
    void up() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('B',1);
        assertEquals(c.up(), new Cordenada('B',1));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo down")
    void down() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('B',1);
        assertEquals(c.down(), new Cordenada('B',3));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo left")
    void left() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('C',2);
        assertEquals(c.left(), new Cordenada('A',2));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo right")
    void right() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('A',2);
        assertEquals(c.right(), new Cordenada('C',2));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo upRight")
    void upRight() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('A',3);
        assertEquals(c.upRight(), new Cordenada('C',1));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo upLeft")
    void upLeft() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('C',3);
        assertEquals(c.upLeft(), new Cordenada('A',1));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo downRight")
    void downRight() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('A',1);
        assertEquals(c.downRight(), new Cordenada('C',3));
        assertFalse(c.equals(c2));
    }

    @Test
    @DisplayName("Prueba del metodo downLeft")
    void downLeft() {
        Cordenada c = new Cordenada('B',2);
        Cordenada c2 = new Cordenada('C',1);
        assertEquals(c.downLeft(), new Cordenada('A',3));
        assertFalse(c.equals(c2));
    }
}