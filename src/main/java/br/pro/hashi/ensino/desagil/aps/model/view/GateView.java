package br.pro.hashi.ensino.desagil.aps.model.view;

import br.pro.hashi.ensino.desagil.aps.model.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener {
    private final Gate gate;

    private final Light light;

    private final JCheckBox inA;
    private final JCheckBox inB;

    private final Switch s0;
    private final Switch s1;

    private final Image image;

    public GateView(Gate gate) {
        super();

        this.gate = gate;

        inA = new JCheckBox();
        inB = new JCheckBox();

        s0 = new Switch();
        s1 = new Switch();

        light = new Light(255, 0, 0, 0, 0, 0);

        if (gate.getInputSize() != 1) {

            add(inA, 40, 30);
            add(inB, 40, 70);


            inA.addActionListener(this);
            inB.addActionListener(this);
        } else {
            add(inA, 40, 50);
            inA.addActionListener(this);
        }


        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        light.connect(0, gate);

        addMouseListener(this);

        update();
    }

    private void update() {
        light.getColor();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        boolean inAState = inA.isSelected();
        boolean inBState = inB.isSelected();

        if (inAState) {
            s0.turnOn();
        } else {
            s0.turnOff();
        }
        if (inBState) {
            s1.turnOn();
        } else {
            s1.turnOff();
        }
        if (gate.getInputSize() != 1) {
            gate.connect(0, s0);
            gate.connect(1, s1);
        } else {
            gate.connect(0, s0);
        }
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Color color;
        boolean result = gate.read();
        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        @SuppressWarnings("IntegerDivisionInFloatingPointContext")
        double m = Math.pow((Math.pow((x - (210 + 25 / 2)), 2) + Math.pow((y - (50 + 25 / 2)), 2)), 0.5);
        // Se o clique foi dentro do quadrado colorido...
        //noinspection IntegerDivisionInFloatingPointContext
        if (m < 25 / 2) {
            if (result) {
                // ...então abrimos a janela seletora de cor...
                color = JColorChooser.showDialog(this, null, light.getColor());

                light.setOnColor(color);
            } else {
                color = JColorChooser.showDialog(this, null, light.getColor());

                light.setOffColor(color);
            }
            // ...e chamamos repaint para atualizar a tela.
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 40, 0, 180, 120, this);

        // Desenha um quadrado cheio.
        g.setColor(light.getColor());
        g.fillOval(210, 50, 25, 25);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
