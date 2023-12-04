package gateway.session.handlers;

import io.netty.channel.ChannelFuture;

import java.nio.channels.Channel;
import java.util.concurrent.Callable;

public class SessionServer implements Callable<Channel> {
    @Override
    public Channel call() throws Exception {
        return null;
    }
}
