package prr.clients;

import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.Communication;

public class BasePlan extends ClientPlan {
    
    public BasePlan(Client client) {
        super(client);
    }

    @Override
    protected long getTextPrice(Communication communication) {
        if (communication.getLength() < 50)
            return (client.getType() instanceof Platinum) ? 0 : 10;
        if (communication.getLength() < 100) {
            if (client.getType() instanceof Gold)
                return 10;
            if (client.getType() instanceof Normal)
                return 16;
            return 4;
        }
        return (client.getType() instanceof Platinum) ? 4 : 2 * communication.getLength();
    }

    @Override
    protected long getCallPrice(Communication communication) {
        return (client.getType() instanceof Normal) ? 20 : 10;
    }

    @Override
    protected long getVideoPrice(Communication communication) {
        if (client.getType() instanceof Normal)
            return 30;
        if (client.getType() instanceof Gold)
            return 20;
        return 10;
    }
}