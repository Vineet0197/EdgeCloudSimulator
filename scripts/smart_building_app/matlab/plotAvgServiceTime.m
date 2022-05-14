function [] = plotAvgServiceTime()

    plotGenericResult(1, 5, 'Service Time (sec)', 'ALL_APPS', '');

    plotGenericResult(2, 5, 'Service Time on Edge (sec)', 'SMART_BUILDING', '');

    plotGenericResult(4, 5, 'Service Time on Mobile (sec)', 'SMART_BUILDING', '');

end