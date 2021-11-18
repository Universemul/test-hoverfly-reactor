package com.example.demo.helpers;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// I don't want to capture reuqest to the localhost domain
public class BypassHoverflyProxySelector extends ProxySelector {
    ArrayList<Proxy> noProxy = new ArrayList<Proxy>();
    ProxySelector defaultproxySelector = ProxySelector.getDefault();
    Set<String> authorizedDomains;

    public BypassHoverflyProxySelector(){
        noProxy.add(Proxy.NO_PROXY);
        authorizedDomains = Set.of("localhost", "127.0.0.1");
    }

    @Override
    public List<Proxy> select(URI uri) {
        String host = uri.getHost().toLowerCase();
        if (authorizedDomains.contains(host)) {
            return noProxy;
        }
        return defaultproxySelector.select(uri);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {

    }
}