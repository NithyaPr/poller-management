import React from 'react';
interface Props {
    moduleName?: string;
    fallback: React.ReactNode;
    children: React.ReactNode;
  }
  
  interface State {
    hasError: boolean;
  }

export class ErrorBoundary extends React.Component<Props, State> {
    constructor(props: Props) {
      super(props);
      this.state = { hasError: false };
    }

    static getDerivedStateFromError() {
      // Update state so the next render will show the fallback UI.
      return { hasError: true };
    }
  
    componentDidCatch(error: any, errorInfo: any) {
      // You can also log the error to an error reporting service
      const { moduleName } = this.props;

        const metadata = moduleName ? { moduleName } : undefined;
      // logErrorToMyService(error, errorInfo);
    }
  
    render() {
        const { hasError } = this.state;
        const { fallback, children } = this.props;
        if (hasError) {
          return fallback;
        }
    
        return children;
      }
  }
  