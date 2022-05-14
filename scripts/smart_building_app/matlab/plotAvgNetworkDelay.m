function [] = plotAvgNetworkDelay()

    plotGenericResult(1, 7, 'Average Network Delay (sec)', 'ALL_APPS', '');

    plotGenericResult(5, 1, 'Average WLAN Delay (sec)', 'SMART_BUILDING', '');
end