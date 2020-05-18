package br.pro.hashi.ensino.desagil.aps.model.model;

public class XnorGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;
    private final NandGate nandFinal;


    public XnorGate() {
        super("XNOR", 2);

        nand1 = new NandGate();

        nand2 = new NandGate();
        nand2.connect(1, nand1);


        nand3 = new NandGate();
        nand3.connect(0,nand1);

        nand4 = new NandGate();
        nand4.connect(0,nand2);
        nand4.connect(1,nand3);

        nandFinal = new NandGate();
        nandFinal.connect(0, nand4);
        nandFinal.connect(1, nand4);
    }

    @Override
    public boolean read() {
        return nandFinal.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0:
                nand2.connect(0, emitter);
                nand1.connect(0, emitter);
                break;
            case 1:
                nand1.connect(1, emitter);
                nand3.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputIndex);
        }
    }
}



