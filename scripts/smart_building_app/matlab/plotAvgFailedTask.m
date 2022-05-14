function [] = plotAvgFailedTask()

    plotGenericResult(1, 2, 'Failed Tasks (%)', 'ALL_APPS', 'percentage_for_all');

    plotGenericResult(2, 2, 'Failed Tasks on Edge (%)', 'SMART_BUILDING', 'percentage_for_completed');

    plotGenericResult(4, 2, 'Failed Tasks on Mobile (%)', 'SMART_BUILDING', 'percentage_for_failed');
    
end