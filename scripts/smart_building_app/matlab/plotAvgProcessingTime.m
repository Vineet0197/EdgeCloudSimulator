function [] = plotAvgProcessingTime()

    plotGenericResult(1, 6, 'Processing Time (sec)', 'ALL_APPS', '');

    plotGenericResult(2, 6, 'Processing Time on Edge (sec)', 'SMART_BUILDING', '');

    plotGenericResult(4, 6, 'Processing Time on Mobile (sec)', 'SMART_BUILDING', '');
    
end